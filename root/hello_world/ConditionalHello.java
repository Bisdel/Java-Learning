package root.hello_world;

public class ConditionalHello {
    public static void main(String[] args) {

        if (args.length==1) {
            sayHelloTo(args[0]);
        } else if (args.length==2) {
            sayHelloTo(args[0] + "-" + args[1]);
        } else if (args.length==3) {
            sayHelloTo(args[0] + "-" + args[1] + "-" + args[2]);
        } else {
            sayHelloTo("world");
        }
 
 }
 
 /** affiche le message hello au destinataire fourni
 * @param recipient
 */
 
    private static void sayHelloTo(String recipient) {
        System.out.println("Hello " + recipient + "!");
     }
 
}
