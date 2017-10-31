(ns all-your-base)

(defn- valid-numeral? [base numeral]
  (and (>= numeral 0) (< numeral base)))

(defn- valid-number? [base number]
  (every? (partial valid-numeral? base) number))

(defn- multiply-and-add [base accumulator next-value]
  (+ (* accumulator base) next-value))

(defn- from-numerals [base numerals]
  (reduce (partial multiply-and-add base) 0 numerals))

(defn- to-numerals [base number]
  (loop [numerals '(), n number]
    (if (zero? n)
      (if (empty? numerals) '(0) numerals)
      (recur (conj numerals (rem n base)) (int (/ n base))))))

(defn convert [base-in number base-out]
  (if (or (<= base-out 1) (<= base-in 1) (not (valid-number? base-in number)))
    nil
    (if (empty? number)
      '()
      (->> number
           (from-numerals base-in)
           (to-numerals base-out)))))
