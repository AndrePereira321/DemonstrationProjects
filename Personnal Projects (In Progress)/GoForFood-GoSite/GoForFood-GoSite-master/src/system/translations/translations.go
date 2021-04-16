package translations

import (
	"errors"
	"system/files"

	"github.com/antchfx/xmlquery"
)

const languages_path = "languages/"

func GetTranslateFilePath(language string) string {
	return languages_path + language + ".xml"
}

func TranslateTag(language string, tag string) (string, error) {
	languageFile := GetTranslateFilePath(language)
	xmlFile, err := files.OpenXML(languageFile)
	if err != nil {
		return "", errors.New("Can't open the language file: " + languageFile)
	}

	insertPrefix(&tag)

	node, err := xmlquery.Query(xmlFile, tag)
	if err != nil {
		return "", errors.New("Error searching the tag: " + tag)
	}

	return getNodeValue(node, tag), nil
}

func TranslateTagFromFile(xmlFile *xmlquery.Node, tag string) (string, error) {
	insertPrefix(&tag)

	node, err := xmlquery.Query(xmlFile, tag)
	if err != nil {
		return "", errors.New("Error searching the tag: " + tag)
	}

	return getNodeValue(node, tag), nil
}

func TranslateTags(language string, tags []string) ([]string, error) {
	languageFile := GetTranslateFilePath(language)
	xmlFile, err := files.OpenXML(languageFile)
	if err != nil {
		return nil, errors.New("Can't open the language file: " + languageFile)
	}
	insertPrefixes(tags)

	result := make([]string, len(tags))

	var node *xmlquery.Node
	for i, tag := range tags {

		node, err = xmlquery.Query(xmlFile, tag)
		if err != nil {
			return nil, errors.New("Error searching the tag: " + tag)
		}

		result[i] = getNodeValue(node, tag)
	}

	return result, nil
}

func getNodeValue(node *xmlquery.Node, tag string) string {
	if node != nil {
		return node.InnerText()
	} else {
		return "[[:" + tag[2:] + ":]]"
	}
}

func insertPrefixes(tags []string) {
	for _, tag := range tags {
		insertPrefix(&tag)
	}
}

func insertPrefix(tag *string) { *tag = "//" + *tag }
