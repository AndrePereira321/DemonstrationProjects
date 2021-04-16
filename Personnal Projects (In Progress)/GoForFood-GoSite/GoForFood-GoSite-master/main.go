package main

import (
	"log"
	"net/http"

	"routes"
	"system/router"
)

func main() {
	router.AddGETRoute("/", routes.IndexGET)
	router.AddGETRoute("/test", routes.TestGET)
	http.HandleFunc("/", router.MainRouter)

	log.Fatal(http.ListenAndServe(":8080", nil))
}
