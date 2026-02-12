package fr.neutronstars.survival.server.request.handler;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.request.RequestHandler;
import fr.neutronstars.survival.server.request.message.input.InputActionRequest;

@Inject("root")
public class InputActionRequestHandler implements RequestHandler<InputActionRequest> {

    @Override
    public Class<InputActionRequest> type() {
        return InputActionRequest.class;
    }

    @Override
    public void handle(InputActionRequest request) {
        request.player().controls().of(request.inputId()).pressed(request.pressed());
    }
}
