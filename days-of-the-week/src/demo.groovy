def yuletide = { start, stop -> (start..stop).findAll { Date.parse("yyyy-MM-dd", "${it}-12-25").format("EEE") == "Sun" } }

println yuletide(2008, 2121)