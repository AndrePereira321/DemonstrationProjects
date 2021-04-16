package router

import (
	"fmt"
	"net/http"
	"strings"
	"system/request"
)

var routeMapGET = map[string]func(*http.ResponseWriter, *http.Request){}
var routeMapPOST = map[string]func(*http.ResponseWriter, *http.Request){}

func MainRouter(w http.ResponseWriter, r *http.Request) {

	if strings.HasPrefix(r.URL.Path, "/resources") {
		http.ServeFile(w, r, r.URL.Path[1:])

	} else {
		executeRoute(r.Method, r.URL.Path, &w, r)
	}
}

func AddPOSTRoute(route string, routeFun func(*http.ResponseWriter, *http.Request)) {
	routeMapPOST[route] = routeFun
}

func AddGETRoute(route string, routeFun func(*http.ResponseWriter, *http.Request)) {
	routeMapGET[route] = routeFun
}

func executeRoute(method, route string, w *http.ResponseWriter, r *http.Request) {

	if method == "GET" {
		executeRouteSafe(&routeMapGET, route, w, r)

	} else if method == "POST" {
		executeRouteSafe(&routeMapPOST, route, w, r)

	} else {
		(*w).WriteHeader(http.StatusMethodNotAllowed)
		redirectPath := request.GetRedirectPath(r)
		fmt.Println("Must do METHOD NOT ALLOWED page with " + redirectPath)
		//TODO METHOD NOT ALLOWED page
	}

}

func executeRouteSafe(
	routeMap *map[string]func(*http.ResponseWriter, *http.Request),
	route string,
	w *http.ResponseWriter,
	r *http.Request) {

	if f, ok := (*routeMap)[route]; ok {
		f(w, r)

	} else {
		(*w).WriteHeader(http.StatusNotFound)
		redirectPath := request.GetRedirectPath(r)
		fmt.Println("Must do NOT FOUND page with " + redirectPath)
		//TODO not found page
	}

}
