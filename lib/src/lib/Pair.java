package lib;

public  class Pair<T, U> {
  public T item1;
  public U item2;

  public Pair(T item1, U item2) {
    super();
    this.item1 = item1;
    this.item2 = item2;
  }

  @Override
  public int hashCode() {
    // ought to be unique enough, right?
    return this.item1.hashCode() * this.item2.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    // ok so don't use it on non-Pair<T, U>'s
    // type erasure sure is nice
    @SuppressWarnings("unchecked")
    Pair<T, U> p = (Pair<T, U>) obj;
    
    return this.item1.equals(p.item1) && this.item2.equals(p.item2);
  }
}
