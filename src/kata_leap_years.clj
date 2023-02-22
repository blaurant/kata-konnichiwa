(ns kata-leap-years
  :require [clojure])

; https://www.mathsisfun.com/leap-years.html



(defn year-div? [year n]
  (zero? (mod year n)))

(defn leap-years? [year]
  (or (year-div? year 400)
      (and (year-div? year 4)
           (not (year-div? year 100)))))

(def leap-years [1996 2000 2004])
(def no-leap-years [1997])

(map leap-years? leap-years)
(map leap-years? no-leap-years)

(defn leap-years2? [year]
  (let [div-by? (partial #(zero? (mod year %)))]
    (or (div-by? 400)
        (and (div-by? 4)
             (not (div-by? 100))))))

(map leap-years2? leap-years)
(map leap-years2? no-leap-years)