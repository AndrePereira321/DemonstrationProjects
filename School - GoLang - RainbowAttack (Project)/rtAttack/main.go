package main

import (
	"bufio"
	"encoding/hex"
	"fmt"

	"./starter"
	"./table"
)

func main() {
	var hash [32]byte
	hashesFile, rainbowTable, chainSize, outputFile := starter.OpenFiles()
	fmt.Println(chainSize)
	hashes := bufio.NewReader(hashesFile)
	inputHash, _, err := hashes.ReadLine()
	for err == nil {
		hashValue, convErr := hex.DecodeString(string(inputHash))
		if convErr != nil || len(hashValue) != 32 {
			fmt.Println("Invalid hash format! Must be SHA256 hash with 64 digits!")
			outputFile.WriteString("Invalid input hash!\n")
		} else {
			fmt.Printf("Cracking hash: %s\n", inputHash)
			copy(hash[:], hashValue)
			result := table.FindPassWord(&hash, chainSize, rainbowTable)
			if result != "" {
				fmt.Printf("Trouve le mot de pass: %s\n", result)
				outputFile.WriteString(result + "\n")
			} else {
				fmt.Println("Pas trouve")
				outputFile.WriteString("Not found!\n")
			}
		}
		inputHash, _, err = hashes.ReadLine()
	}
	hashesFile.Close()
	outputFile.Close()
	rainbowTable.Close()

}
