package com.example.movieservice;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkTests {
    @Test
    void sink_replay(){
        Sinks.Many<Integer> replaySink=Sinks.many().replay().all();

        replaySink.emitNext(1,Sinks.EmitFailureHandler.FAIL_FAST);
        replaySink.emitNext(2,Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux = replaySink.asFlux();
        integerFlux.subscribe((i)->{
            System.out.println(i);
        });
        Flux<Integer> integerFlux1 = replaySink.asFlux();
        integerFlux1.subscribe((i)->{
            System.out.println(i);
        });
        replaySink.tryEmitNext(2);

    }


    //in multicast the latest subscriber will consider /receive the events
    // after the subscription is happened and other subscribers will consider all the events.
    @Test
    void sink_multicast(){
        Sinks.Many<Integer> multicast=Sinks.many().multicast().onBackpressureBuffer();

        multicast.emitNext(1,Sinks.EmitFailureHandler.FAIL_FAST);
        multicast.emitNext(2,Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux = multicast.asFlux();
        integerFlux.subscribe((i)->{
            System.out.println("sub1 "+i);
        });
        Flux<Integer> integerFlux1 = multicast.asFlux();
        integerFlux1.subscribe((i)->{
            System.out.println("sub2 "+i);
        });
        multicast.tryEmitNext(3);

    }



    //only one subscriber is allowed.
    @Test
    void sink_unicast(){
        Sinks.Many<Integer> multicast=Sinks.many().unicast().onBackpressureBuffer();

        multicast.emitNext(1,Sinks.EmitFailureHandler.FAIL_FAST);
        multicast.emitNext(2,Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux = multicast.asFlux();
        integerFlux.subscribe((i)->{
            System.out.println("sub1 "+i);
        });
//        Flux<Integer> integerFlux1 = multicast.asFlux();
//        integerFlux1.subscribe((i)->{
//            System.out.println("sub2 "+i);
//        });
        multicast.tryEmitNext(3);

    }


}
