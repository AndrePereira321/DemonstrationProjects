#include <iostream>
#include <filesystem>
#include <vector>
#include "TableFile.hpp"

using namespace std;

namespace fs = std::filesystem;

/**
 * @brief mergeSort
 * Merges the different sorted files generated by rtGenerator into
 * a singled sorted file.
 * The repetitions are allowed to not reduce the succes rate.
 */
void mergeSort(vector<TableFile> & files, ofstream & output)
{
    static unsigned minIndex, i;
    static std::string min, tail;
    while (!files.empty())
    {
        min = files[0].getTail();
        minIndex = 0;
        for (i = 1; i < files.size(); i++)
        {
            tail = files[i].getTail();
            if (tail < min)
            {
                min = tail;
                minIndex = i;
            }
        }
        output.write(files[minIndex].getBuff(), LINE_SIZE);
        if (!files[minIndex].next())
            files.erase(files.begin() + minIndex);
    }
}

int main(int argc, const char * argv[])
{
    if (argc < 3)
    {
        cerr << "Bad Usage!" << endl;
        cerr << "Execute With: " << endl;
        cerr << "rtGenerator <generatedFolder> <outputFile>" << endl;
        return -1;
    }
    vector<TableFile> files;
    ofstream output(argv[2]);
    bool hasInfo = false;
    //Iterates over all files of the given folder
    for (const auto & entry : fs::directory_iterator(argv[1]))
    {
        if (entry.path().string().find("INFO.txt") != std::string::npos)
        {
            //Writes the table info (chainLength) into the first line.
            char chainSize[LINE_SIZE];
            std::ifstream infoFile(entry.path());
            infoFile.getline(chainSize, LINE_SIZE);
            output.write(chainSize, LINE_SIZE);
            hasInfo = true;
        }
        else
        {
            files.push_back(TableFile(entry.path()));
        }
    }
    if (!hasInfo)
    {
        cerr << "Invalid input folder!" << endl;
        return -1;
    }
    mergeSort(files, output);
    return 0;
}

