package request

import (
	"net/http"
	"strings"
	"system/info"
)

func GetLanguage(r *http.Request) string {
	language := strings.Split(r.Header.Get("Accept-Language"), ",")[0]
	for _, val := range info.SUPPORTED_LANGUAGES {
		if val == language {
			return language
		}
	}
	return info.DEFAULT_LANGUAGE
}

func GetRedirectPath(r *http.Request) string {
	redirectPath := r.Header.Get("Referer")
	if redirectPath == "" {
		redirectPath = "/"
	}
	return redirectPath
}
