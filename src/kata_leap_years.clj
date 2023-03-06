(ns kata-leap-years
  :require [clojure])

; https://www.mathsisfun.com/leap-years.html

;Leap Year
;A normal year has 365 days.
;A Leap Year has 366 days (the extra day is the 29th of February).
;How to know if it is a Leap Year:
;yes	Leap Years are any year that can be exactly divided by 4 (such as 2016, 2020, 2024, etc)
;not	except if it can be exactly divided by 100, then it isn't (such as 2100, 2200, etc)
;yes	except if it can be exactly divided by 400, then it is (such as 2000, 2400)




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