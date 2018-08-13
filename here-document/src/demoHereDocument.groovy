// Groovy has two types of multi-line strings, which behave similarly to "here documents"

// - Multi-line String literal
//   The literal text, preserving lines and spacing

println '''
Time's a strange fellow;
                        more he gives than takes
(and he takes all) nor any marvel finds
quite disappearance but some keener makes
losing, gaining
               --love! if a world ends
'''


// Output:

//Time's a strange fellow;
//                        more he gives than takes
//(and he takes all) nor any marvel finds
//quite disappearance but some keener makes
//losing, gaining
//               --love! if a world ends


// - Multi-line GString expression
//   Like single-line GString expressions, any subexpression delimited with ${ } is substituted
//   with its "toString()" value. Preserves lines and spacing outside of the subexpressions.

def expired='defunct'
def horse='stallion'
def christ='Jesus'
 
println """
Buffalo Bill's 
              ${expired} 
                          who used to 
                          ride a watersmooth-silver 
                                                                  ${horse} 
              and break onetwothreefourfive pigeonsjustlikethat 
                                                                                           ${christ}
 
              he was a handsome man 
                                                    and what i want to know is 
              how do you like your blueeyed boy 
              Mister Death
"""


// Output:

//Buffalo Bill's 
//              defunct 
//                          who used to 
//                          ride a watersmooth-silver 
//                                                                  stallion 
//              and break onetwothreefourfive pigeonsjustlikethat 
//                                                                                           Jesus
//
//              he was a handsome man 
//                                                    and what i want to know is 
//              how do you like your blueeyed boy 
//              Mister Death
