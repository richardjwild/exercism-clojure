(ns beer-song
  (require [clojure.string :as str]))

(defn bottles-of-beer [n]
  (str (case n
         0 "no more bottles"
         1 "1 bottle"
         (str n " bottles")) " of beer"))

(defn bottles-of-beer-on-the-wall [n]
  (str (bottles-of-beer n) " on the wall"))

(defn what-to-do [n]
  (if (= 0 n) "Go to the store and buy some more, "
    (str "Take " (if (= n 1) "it" "one") " down and pass it around, ")))

(defn verse [n]
  (str (str/capitalize (bottles-of-beer-on-the-wall n)) ", " (bottles-of-beer n) ".\n"
       (what-to-do n) (bottles-of-beer-on-the-wall (if (= 0 n) 99 (dec n))) ".\n"))

(defn sing
  ([verse-from verse-to]
   (if (= verse-from verse-to)
     (verse verse-from)
     (str (verse verse-from) "\n" (sing (dec verse-from) verse-to))))
  ([verse-from]
   (sing verse-from 0)))