package main

import (
	"fmt"
	"github.com/gomodule/redigo/redis"
	"log"
	"time"
)

func main() {
	fmt.Println("Go Redis PUB")

	c, err := redis.Dial("tcp", "localhost:6380")
	
	if err != nil {
    	log.Println(err)
	}

	log.Println(c)


	for i := 0; i < 5; i++ {
		//Publish a message ...
		c.Do("PUBLISH", "example", "hello "+time.Now().String())
		time.Sleep(3 * time.Second)
	}

}