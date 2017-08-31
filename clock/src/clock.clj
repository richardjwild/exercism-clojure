(ns clock)

(defn minutes [in]
  (let [quotient (quot in 60) remainder (rem in 60)]
    {:value (+ remainder (if (neg? remainder) 60 0)),
     :carry (- quotient (if (neg? remainder) 1 0))}))

(defn hours [in carry]
  (let [remainder (rem (+ in carry) 24)]
    (+ remainder (if (neg? remainder) 24 0))))

(defn clock [hours-in minutes-in]
  (let [m (minutes minutes-in)]
    [(hours hours-in (:carry m)) (:value m)]))

(defn clock->string [[hours minutes]]
  (format "%02d:%02d" hours minutes))

(defn add-time [[hours minutes] to-add]
  (clock hours (+ minutes to-add)))
