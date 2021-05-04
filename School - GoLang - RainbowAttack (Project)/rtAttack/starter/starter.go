package starter

import (
	"fmt"
	"os"
	"strconv"
)

//Open the app files the files with the right permissions
//reading the command line arguments.
func OpenFiles() (*os.File, *os.File, int, *os.File) {
	if len(os.Args) < 4 {
		displayBadUsage()
		os.Exit(-1)
	}
	hashesFile := openHashes(os.Args[1])
	rainbowTable, chainSize := openTable(os.Args[2])
	outputFile := createOutputFile(os.Args[3])
	return hashesFile, rainbowTable, chainSize, outputFile
}

func openHashes(hashFile string) *os.File {
	file, err := os.Open(hashFile)
	if err != nil {
		fmt.Println("Invalid hash file!")
		panic(err)
	}
	return file
}

func createOutputFile(outputFile string) *os.File {
	file, err := os.Create(outputFile)
	if err != nil {
		fmt.Println("Can't create output file!")
		panic(err)
	}
	return file
}

//Opens the file corresponding the rainbow table and reads the
//chain length of each chain of the file.
func openTable(rainbowTable string) (*os.File, int) {
	file, err := os.Open(rainbowTable)
	if err != nil {
		fmt.Println("Invalid Rainbow Table File!")
		panic(err)
	}
	chainSizeParser := make([]byte, 20)
	file.Read(chainSizeParser)
	i := 0
	for chainSizeParser[i] != 0 {
		i++
	}
	chainSize, err := strconv.Atoi(string(chainSizeParser[0:i]))
	if err != nil {
		fmt.Println("Invalid Rainbow Table Format!")
		panic(err)
	}
	return file, chainSize
}

func displayBadUsage() {
	fmt.Println("Not enought arguments!")
	fmt.Println("Usage: ")
	fmt.Println("./rtAttack <hashesFile> <rainbowTableFile> <outputFile>")
}
