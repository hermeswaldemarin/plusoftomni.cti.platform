package br.com.omniplusoft.gateway.domain.ctiplatform.exampleimpl;

import br.com.omniplusoft.gateway.domain.ctiplatform.CTIResponse;
import br.com.omniplusoft.gateway.domain.ctiplatform.CallbackDispatcher;
import br.com.omniplusoft.gateway.domain.ctiplatform.event.ConsultEvent;
import br.com.omniplusoft.gateway.infrastructure.ctiplatform.CTIEvents;
import br.com.omniplusoft.gateway.infrastructure.ctiplatform.annotation.EventHandler;
import br.com.omniplusoft.gateway.infrastructure.ctiplatform.annotation.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
@EventHandler
@Profile("example")
public class OmniCTIConsultHandler {

    @Autowired
    CallbackDispatcher callbackDispatcher;

    @Handle(CTIEvents.CONSULT)
    public void execute(ConsultEvent event){
        event.getArguments();

        callbackDispatcher.dispatch(new CTIResponse("status", 0, "Consult completed.", Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("arg1", "one"),
                new AbstractMap.SimpleEntry<>("arg2", "two"))
                .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())))));
    }
}