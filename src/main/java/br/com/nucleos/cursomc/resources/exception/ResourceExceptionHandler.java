package br.com.nucleos.cursomc.resources.exception;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.nucleos.cursomc.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler implements Serializable {
   private static final long serialVersionUID = 1L;

   @ExceptionHandler(ObjectNotFoundException.class)
   public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
      StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
   }
}
