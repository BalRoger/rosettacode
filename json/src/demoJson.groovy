// Solution requires Groovy 1.8 or later.
// Note that JsonSlurper accepts an extra comma such as [1,2,3,]. This is an extension to the [JSON grammar].

def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText('''
{
    "people":[
        {"name":{"family":"Flintstone","given":"Frederick"},"age":35,"relationships":{"wife":"people[1]","child":"people[4]"}},
        {"name":{"family":"Flintstone","given":"Wilma"},"age":32,"relationships":{"husband":"people[0]","child":"people[4]"}},
        {"name":{"family":"Rubble","given":"Barnard"},"age":30,"relationships":{"wife":"people[3]","child":"people[5]"}},
        {"name":{"family":"Rubble","given":"Elisabeth"},"age":32,"relationships":{"husband":"people[2]","child":"people[5]"}},
        {"name":{"family":"Flintstone","given":"Pebbles"},"age":1,"relationships":{"mother":"people[1]","father":"people[0]"}},
        {"name":{"family":"Rubble","given":"Bam-Bam"},"age":1,"relationships":{"mother":"people[3]","father":"people[2]"}}
    ]
}
''')


// Test:

result.each { println it.key; it.value.each {person -> println person} }
 
assert result.people[0].name == [family:'Flintstone', given:'Frederick']
assert result.people[4].age == 1
assert result.people[2].relationships.wife == 'people[3]'
assert result.people[3].name == [family:'Rubble', given:'Elisabeth']
assert Eval.x(result, 'x.' + result.people[2].relationships.wife + '.name') == [family:'Rubble', given:'Elisabeth']
assert Eval.x(result, 'x.' + result.people[1].relationships.husband + '.name') == [family:'Flintstone', given:'Frederick']