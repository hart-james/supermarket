package main

import (
	"fmt"
	"github.com/gomodule/redigo/redis"
	"log"
)

func main() {
	fmt.Println("Go Redis SUB")

	c, err := redis.Dial("tcp", "localhost:6380")
	
	if err != nil {
    	log.Println(err)
	}

	log.Println(c)


	psc := redis.PubSubConn{Conn: c}
	psc.Subscribe("example")
	for {
		switch v := psc.Receive().(type) {
		case redis.Message:
			fmt.Printf("%s: message: %s\n", v.Channel, v.Data)
		case redis.Subscription:
			fmt.Printf("%s: %s %d\n", v.Channel, v.Kind, v.Count)
		case error:
			fmt.Println(v)
		}
	}

	
}
