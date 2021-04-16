package routes

import (
	"fmt"
	"net/http"

	"system/translations"
)

func TestGET(w *http.ResponseWriter, r *http.Request) {
	fmt.Println(translations.TranslateTag("pt-PT", "common/welcome-msg"))
}
