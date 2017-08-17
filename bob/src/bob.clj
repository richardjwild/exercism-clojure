(ns bob
  (:require [clojure.string :as string]))

(def responses {
                "Does this cryogenic chamber make me look fat?", "Sure.",
                "4?",                                            "Sure.",
                "WATCH OUT!",                                    "Whoa, chill out!",
                "WHAT THE HELL WERE YOU THINKING?",              "Whoa, chill out!",
                "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!", "Whoa, chill out!",
                "1, 2, 3 GO!",                                   "Whoa, chill out!",
                "I HATE YOU",                                    "Whoa, chill out!",
                "",                                              "Fine. Be that way!"})

(defn response-for [s]
  (get responses (string/trim s) "Whatever."))
