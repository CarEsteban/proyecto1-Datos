(defun fahrenheit-to-celsius (f)
  (/ (* (- f 32) 5) 9))

(defun main ()
  (format t "Ingrese la temperatura en grados Fahrenheit: ")
  (force-output) ; Añadir esta línea para forzar la salida antes de la entrada
  (let ((fahrenheit (read)))
    (format t "La temperatura en grados Celsius es ~a~%" (fahrenheit-to-celsius fahrenheit))))

(main)
