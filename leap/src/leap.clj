(ns leap)

(defn leap-year? [year]
  (if (= 0 (rem year 100))
    (= 0 (rem year 400))
    (= 0 (rem year 4))))