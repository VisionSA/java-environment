package
{
  [Bindable]
  public class SimpleItem
  {
    public var name:String;
    public var guapo:Boolean;
    
    public function SimpleItem(name:String, guapo:Boolean)
    {
      this.name = name;
      this.guapo = guapo;
    }
  }
}