package routes

import (
	"fmt"
	"net/http"

	"pages"
	"system/info"
	"system/request"
	"system/templates"
)

func IndexGET(w *http.ResponseWriter, r *http.Request) {
	page, err := pages.GetBasePage(info.APP_TITLE, "src/pages/content/index/index.html", request.GetLanguage(r))
	tpl, err := templates.ParseFile(pages.BASE_PAGE_NAME, pages.BASE_PAGE_PATH)
	if err != nil {
		fmt.Println("TODO Internal server")
		//TODO internal server
	}
	tpl.Execute(*w, page)

}
