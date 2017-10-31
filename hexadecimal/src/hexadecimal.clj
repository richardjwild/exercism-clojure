(ns hexadecimal)

(def numerals
  {\0 0, \1 1, \2 2, \3 3, \4 4, \5 5, \6 6, \7 7,
   \8 8, \9 9, \a 10, \b 11, \c 12, \d 13, \e 14, \f 15})

(def base (count numerals))

(defn- valid-numeral? [digit]
  (contains? numerals digit))

(defn- valid-number? [number]
  (every? valid-numeral? number))

(defn- multiply-and-add [accumulator next-value]
  (+ (* accumulator base) (numerals next-value)))

(defn hex-to-int [hex]
  (if (valid-number? hex)
    (reduce multiply-and-add 0 hex)
    0))