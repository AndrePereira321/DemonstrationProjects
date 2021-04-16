package loaders

import (
	"errors"
	"html/template"
	"io/ioutil"
	"regexp"
	"system/translations"

	"github.com/antchfx/xmlquery"
)

var translationRegex = regexp.MustCompile(`\[\[:(.*?):\]\]`)

func ReadHTMLFile(filepath string, xmlFile *xmlquery.Node) (*template.HTML, error) {
	var tag, traduction string
	var err error

	buff, err := ioutil.ReadFile(filepath)

	if err != nil {
		return nil, errors.New("Can't read/open HTML file: " + filepath)
	}

	//Translate file
	translatedFile := translationRegex.ReplaceAllFunc(buff, func(b []byte) []byte {
		tag = string(b[3 : len(b)-3])
		traduction, err = translations.TranslateTagFromFile(xmlFile, tag)
		if err == nil {
			return []byte(traduction)
		} else {
			return b
		}
	})

	tpl := template.HTML(translatedFile)
	return &tpl, nil
}
