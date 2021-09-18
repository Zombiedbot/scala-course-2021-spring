package karazin.scala.users.group.week10.homework

object polymorphicfunctions extends App:
  
  /*
    Provide an implementation for the next functions
  */

  val `I₍₂,₄₎⁴`: [α1, α2, α3, α4] ⇒ α1 ⇒ α2 ⇒ α3 ⇒ α4 ⇒ (α2, α4) =
    [α1, α2, α3, α4] ⇒ (x1: α1) ⇒ (x2: α2) ⇒ (x3: α3) ⇒ (x4: α4) ⇒ (x2, x4)
  val `(f ० g ० h ० i)(x)`: [ε, δ, γ, β, α] ⇒ (δ ⇒ ε) ⇒ ((γ ⇒ δ) ⇒ ((β ⇒ γ) ⇒ ((α ⇒ β) ⇒ (α ⇒ ε)))) =
    [ε, δ, γ, β, α] ⇒ (f: (δ ⇒ ε)) ⇒ (g: (γ ⇒ δ)) ⇒ (h: (β ⇒ γ)) ⇒ (i: (α ⇒ β)) ⇒ (x: α) ⇒ f(g(h(i(x))))

end polymorphicfunctions
