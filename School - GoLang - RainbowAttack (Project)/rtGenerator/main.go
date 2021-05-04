package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"

	"./tableGenerator"
)

func main() {
	tableFolder, lineCount, chainLength := getArgs()
	createFolder(tableFolder, chainLength)
	rand.Seed(time.Now().UTC().UnixNano())
	tableGenerator.GenerateTable(tableFolder, chainLength, lineCount)
}

//Retrieve the command line arguments for generating the table.
func getArgs() (tablePath string, lineCount, chainLength int) {
	if len(os.Args) < 4 {
		fmt.Println("Bad Usage!")
		fmt.Println("Execute with: ")
		fmt.Println("rtGenerator <outputTable> <lineCount> <chainLength>")
		os.Exit(-1)
		return
	}
	lineCount, err := strconv.Atoi(os.Args[2])
	if err != nil {
		fmt.Println("*** INVALID LINE COUNT ****")
		panic(err)
	}
	chainLength, err = strconv.Atoi(os.Args[3])
	if err != nil {
		fmt.Println("*** INVALID CHAIN LENGTH ****")
		panic(err)
	}
	return os.Args[1], lineCount, chainLength

}

//Creates the folder of the table and writes the info of the table
//in the INFO.txt file.
func createFolder(tableFolder string, chainLength int) {
	os.Mkdir(tableFolder, 0733)
	file, _ := os.Create("./" + tableFolder + "/INFO.txt")
	file.WriteString(strconv.Itoa(chainLength) + "\n")
}
