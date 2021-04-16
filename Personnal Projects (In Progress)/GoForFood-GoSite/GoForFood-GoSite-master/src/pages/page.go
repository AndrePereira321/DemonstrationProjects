package pages

import (
	"html/template"

	"system/files"
	"system/loaders"
	"system/translations"
)

const common_css_links = "src/pages/common/css_links.html"
const common_script_links = "src/pages/common/script_links.html"
const common_meta = "src/pages/common/meta.html"
const main_nav = "src/pages/content/main_nav.html"
const main_footer = "src/pages/content/main_footer.html"

const BASE_PAGE_PATH = "src/pages/base_page.html"
const BASE_PAGE_NAME = "base_page.html"

type BasePage struct {
	CSS_LINKS    *template.HTML
	SCRIPT_LINKS *template.HTML
	Meta         *template.HTML
	Title        *string
	Nav          *template.HTML
	Main         *template.HTML
	Footer       *template.HTML
}

func GetBasePage(pageTitle, contentPath, language string) (*BasePage, error) {
	xmlFile, err := files.OpenXML(translations.GetTranslateFilePath(language))
	if err != nil {
		return nil, err
	}

	cssLinks, err := loaders.ReadHTMLFile(common_css_links, xmlFile)
	if err != nil {
		return nil, err
	}

	scriptLinks, err := loaders.ReadHTMLFile(common_script_links, xmlFile)
	if err != nil {
		return nil, err
	}

	meta, err := loaders.ReadHTMLFile(common_meta, xmlFile)
	if err != nil {
		return nil, err
	}

	nav, err := loaders.ReadHTMLFile(main_nav, xmlFile)
	if err != nil {
		return nil, err
	}

	main, err := loaders.ReadHTMLFile(contentPath, xmlFile)
	if err != nil {
		return nil, err
	}

	footer, err := loaders.ReadHTMLFile(main_footer, xmlFile)
	if err != nil {
		return nil, err
	}

	basePage := BasePage{
		CSS_LINKS:    cssLinks,
		SCRIPT_LINKS: scriptLinks,
		Meta:         meta,
		Title:        &pageTitle,
		Nav:          nav,
		Main:         main,
		Footer:       footer,
	}
	return &basePage, nil
}
