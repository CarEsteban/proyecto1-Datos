(defun factorial (n)
  (if (<= n 1)
      1
      (* n (factorial (- n 1)))))

(defun fibonacci (n)
  (if (<= n 1)
      n
      (+ (fibonacci (- n 1)) (fibonacci (- n 2)))))

(defun main ()
  (format t "Ingrese un número para calcular el factorial y Fibonacci: ")
  (force-output) ; Añadir esta línea para forzar la salida antes de la entrada
  (let ((num (read)))
    (format t "Factorial de ~a es ~a~%" num (factorial num))
    (format t "Fibonacci de ~a es ~a~%" num (fibonacci num))))

(main)
