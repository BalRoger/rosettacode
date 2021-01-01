class Complex {
    final Number real, imag

    static final Complex i = [0,1] as Complex

    Complex(Number r, Number i = 0) { (real, imag) = [r, i] }

    Complex(Map that) { (real, imag) = [that.real ?: 0, that.imag ?: 0] }

    Complex plus (Complex c) { [real + c.real, imag + c.imag] as Complex }
    Complex plus (Number n) { [real + n, imag] as Complex }

    Complex minus (Complex c) { [real - c.real, imag - c.imag] as Complex }
    Complex minus (Number n) { [real - n, imag] as Complex }

    Complex multiply (Complex c) { [real*c.real - imag*c.imag , imag*c.real + real*c.imag] as Complex }
    Complex multiply (Number n) { [real*n , imag*n] as Complex }

    Complex div (Complex c) { this * c.recip() }
    Complex div (Number n) { this * (1/n) }

    Complex negative () { [-real, -imag] as Complex }

    /** the complex conjugate of this complex number. */
    Complex getConjugate () { [real, -imag] as Complex }
    /** the conjugate */
    Complex conjugate () { this.conjugate }
    /** the conjugate */
    static Complex conjugate(Complex c) { c.conjugate }
    /** the conjugate. Overloads the bitwise complement (~) operator. */
    Complex bitwiseNegate () { this.conjugate }

    /** the reciprocal of this complex number. */
    Complex getRecip() { (~this) / (ρ**2) }
    /** the reciprocal of this complex number. */
    Complex recip() { this.recip }

    /** the magnitude (or modulus, or absolute value) ρ (rho) of this complex number. */
    // could also use Math.sqrt( (this * (~this)).real )
    Number getMagnitude() { Math.sqrt( real*real + imag*imag ) }
    /** the modulus of this complex number. */
    Number getModulus() { this.magnitude }
    /** the absolute value of this complex number. */
    Number getAbs() { this.magnitude }
    /** the absolute value of this complex number. */
    Number abs() { this.magnitude }
    /** the absolute value of this complex number. */
    static Number abs(Complex c) { c.magnitude }
    /** derived polar magnitude ρ (rho) for polar form. */
    Number getRho() { this.magnitude }
    /** derived polar magnitude ρ (rho) for polar form. */
    Number getΡ() { this.magnitude } // this is greek uppercase rho (Ρ), not roman P
                                     // the property referenced will be lowercase rho (ρ)

    /** the argument, the derived polar angle θ (theta) for polar form. Normalized to 0 ≤ θ < 2π. */
    Number getArgument() {
        def θ = Math.atan2(imag,real)
        θ = θ < 0 ? θ + 2 * Math.PI : θ
    }
    /** the argument, θ (theta) */
    Number getArg() { this.argument }
    /** the argument, θ (theta) */
    Number arg() { this.argument }
    /** the argument, θ (theta) */
    static Number arg(Complex c) { c.argument }
    /** the argument, θ (theta) */
    Number getTheta() { this.argument }
    /** the argument, θ (theta) */
    Number getΘ() { this.argument} // this is greek uppercase theta (Θ)
                                   // the property referenced will be lowercase theta (θ)

    /** Runs Euler's polar-to-Cartesian complex conversion,
     * converting [ρ, θ] inputs into a [real, imag]-based complex number */
    static Complex fromPolar(Number ρ, Number θ) {
        [ρ * Math.cos(θ), ρ * Math.sin(θ)] as Complex
    }

    /** Creates new complex with same magnitude ρ, but different angle θ */
    Complex withTheta(Number θ) { fromPolar(this.ρ, θ) }
    /** Creates new complex with same magnitude ρ, but different angle θ */
    Complex withΘ(Number θ) { fromPolar(this.ρ, θ) }

    /** Creates new complex with same angle θ, but different magnitude ρ */
    Complex withRho(Number ρ) { fromPolar(ρ, this.θ) }
    /** Creates new complex with same angle θ, but different magnitude ρ */
    Complex withΡ(Number ρ) { fromPolar(ρ, this.θ) } // this is greek uppercase rho, not roman P

    static Complex exp(Complex c) { fromPolar(Math.exp(c.real), c.imag) }

    static Complex log(Complex c) { [Math.log(c.ρ), c.θ] as Complex }

    Complex power(Complex c) {
        ( this == 0 && c != 0
                ?  ([0] as Complex)
                :  c == 1
                        ?  this
                        :  exp( log(this) * c ) )
    }

    Complex power(Number n) { this ** ([n] as Complex) }

    boolean equals(that) {
        ( that != null && (that instanceof Complex
                ? [this.real, this.imag] == [that.real, that.imag]
                : that instanceof Number && [this.real, this.imag] == [that, 0]) )
    }

    int hashCode() { [real, imag].hashCode() }

    String toString() {
        def realPart = "${real}"
        def imagPart = imag.abs() == 1 ? "i" : "${imag.abs()}i"
        ( imag == 0
                ? realPart
                : real == 0
                        ? (imag > 0 ? '' : "-")  + imagPart
                        : realPart + (imag > 0 ? " + " : " - ") + imagPart )
    }
}
