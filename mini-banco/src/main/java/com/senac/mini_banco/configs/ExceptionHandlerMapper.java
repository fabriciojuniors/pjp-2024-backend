package com.senac.mini_banco.configs;

import com.senac.mini_banco.exceptions.RegraNegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionHandlerMapper {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handle(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(404);
        problemDetail.setTitle("Entidade não encontrada");
        problemDetail.setDetail(ex.getMessage());

        problemDetail.setProperty("dataHoraErro", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm")));

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ProblemDetail> handle(RegraNegocioException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(404);
        problemDetail.setTitle("Operação não pode ser concluída.");
        problemDetail.setDetail(ex.getMessage());

        problemDetail.setProperty("dataHoraErro", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm")));
        problemDetail.setProperty("numeroRegraNegocio", ex.getNumeroRegraNegocio());

        return ResponseEntity.of(problemDetail).build();
    }
}
