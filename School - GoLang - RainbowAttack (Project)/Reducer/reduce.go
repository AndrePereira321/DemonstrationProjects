package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"os"
	"strconv"
)

const charSet = "azertyuiopqsdfghjklmwxcvbnAZERTYUIOPQSDFGHJKLMWXCVBN1234567890"

const channelSize = 50000

func reduce(hash [32]byte, pw []byte, colIndex int) {
	for i := range pw {
		index := hash[(i+colIndex)%32]
		pw[i] = charSet[index%62]
	}

}

func main() {
	mot := []byte(os.Args[1])
	taille, _ := strconv.Atoi(os.Args[2])
	for i := 0; i < taille; i++ {
		hash := sha256.Sum256(mot)
		fmt.Printf("%s - %s\n", mot, hex.EncodeToString(hash[:]))
		reduce(hash, mot, i)
	}
	fmt.Printf("%s\n", string(mot))
}
