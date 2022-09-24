package br.com.alurafood.pedidos.controller;

import br.com.alurafood.pedidos.dto.OrderDto;
import br.com.alurafood.pedidos.dto.StatusDto;
import br.com.alurafood.pedidos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {


        private final OrderService service;

        @GetMapping()
        public List<OrderDto> findAll() {
            return service.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> findById(@PathVariable @NotNull Long id) {
            OrderDto dto = service.findById(id);

            return  ResponseEntity.ok(dto);
        }

        @PostMapping()
        public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto dto, UriComponentsBuilder uriBuilder) {
            OrderDto orderMade = service.create(dto);

            URI address = uriBuilder.path("/pedidos/{id}").buildAndExpand(orderMade.getId()).toUri();

            return ResponseEntity.created(address).body(orderMade);

        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody StatusDto status){
           OrderDto dto = service.update(id, status);

            return ResponseEntity.ok(dto);
        }


        @PutMapping("/{id}/pago")
        public ResponseEntity<Void> approvePaymentOrder(@PathVariable @NotNull Long id) {
            service.approvePaymentOrder(id);

            return ResponseEntity.ok().build();

        }
}
