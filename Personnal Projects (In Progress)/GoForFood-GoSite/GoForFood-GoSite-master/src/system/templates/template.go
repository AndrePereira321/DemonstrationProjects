package templates

import (
	"html/template"
)

func ParseFile(filename, filepath string) (*template.Template, error) {
	return template.New(filename).Delims("[[#", "#]]").ParseFiles(filepath)
}
