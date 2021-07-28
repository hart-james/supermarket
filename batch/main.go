package main

import (
	"fmt"
	"net/http"
	"strconv"
)

/*This script will get run as a Cron job every day at 04:00am (0 4 * * *). 
The command "go run ~/Documents/main.go will be entered into the crontab
and the cron daemon started. This is a low effort strategy towards batching*/

func main() {
	fmt.Println("Batch Service")
	
	URL := "https://jsonplaceholder.typicode.com/todos/"
	
	//Batch job example
	for i := 0; i < 10; i++ {

		s := strconv.Itoa(i)
		resp, err := http.Get(URL + s)
		if err != nil {
			fmt.Println(err)
		}

		fmt.Println(resp)
	}
}