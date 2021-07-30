package main

import (
	"log"
	"net/http"

	"github.com/julienschmidt/httprouter"
)

func main () {
	router := httprouter.New()

	//Map to method
	router.ServeFiles("/training/*filepath", http.Dir("./"))

	log.Fatal(http.ListenAndServe(":9090", router))
}