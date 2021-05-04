#ifndef TABLEFILE_HPP
#define TABLEFILE_HPP

#include <fstream>
#include <string>

#define LINE_SIZE 20

/**
 * @brief The TableFile class
 * Represnts one generated file by rtGenerator.
 */
class TableFile
{
  private:
    /**
     * @brief in_
     * Input file.
     */
    std::ifstream in_;
    /**
     * @brief buff_
     * The buffer usd to read one line.
     */
    char buff_[LINE_SIZE + 1];

  public:
    //Constructor
    inline TableFile(std::string filePath) : in_{filePath}
    {
        in_.read(buff_, LINE_SIZE);
        buff_[LINE_SIZE] = 0;
    }
    /**
     * @brief next
     * Reads the next line.
     * Returns false in EOF.
     */
    inline bool next()
    {
        in_.read(buff_, LINE_SIZE);
        return in_.good();
    }

    /**
     * @brief getBuff
     * Retrieves the entire line (some chars may not be used.
     */
    inline const char * getBuff()
    {
        return buff_;
    }

    /**
     * @brief getTail
     * Returns the tail of the chain(line).
     */
    inline std::string getTail()
    {
        return std::string(&buff_[10]);
    }
};

#endif // TABLEFILE_HPP
