package files

import (
	"bytes"
	"errors"
	"io/ioutil"

	"github.com/antchfx/xmlquery"
)

func OpenXML(filepath string) (*xmlquery.Node, error) {
	xmlBuffer, err := ioutil.ReadFile(filepath)
	if err != nil {
		return nil, errors.New("Can't open/read XML file: " + filepath)
	}

	xmlRoot, err := xmlquery.Parse(bytes.NewReader(xmlBuffer))
	if err != nil {
		return nil, errors.New("Can't parse XML file: " + filepath)
	}
	return xmlRoot, nil
}
