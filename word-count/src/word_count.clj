(ns word-count
  (require [clojure.string :as str]))

(defn is-allowed-character [c] (or (= \space c) (Character/isLetter c) (Character/isDigit c)))

(defn word-count [input-string]
  (frequencies
    (filter not-empty
            (str/split
              (str/lower-case
                (apply str (filter is-allowed-character input-string)))
              #" "))))
