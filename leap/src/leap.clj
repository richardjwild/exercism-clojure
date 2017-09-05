(ns leap)

(defn leap-year? [year]
  (if (zero? (rem year 100))
    (zero? (rem year 400))
    (zero? (rem year 4))))