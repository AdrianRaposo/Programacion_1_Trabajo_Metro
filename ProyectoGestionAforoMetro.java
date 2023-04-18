//Importamos la clase scanner
import java.util.Scanner;
public class ProyectoGestionAforoMetro{
  //función que comprueba si existe la estación
  public static boolean comprobarEstacion(String palabra,String [] lista){
 // Comparamos elemento a elemento los strings del array con el string dado, y vemos si coincide con alguno.
    boolean encontrado=false;
    for (int i=0;i<lista.length&&!encontrado;i++){
      if (lista[i].equalsIgnoreCase(palabra)){
        encontrado=true;
      }
    }
    return encontrado;//Devuelve un valor booleano 
  }
  //función que comprueba si el bono está caducado o no.
  //Si el bono ha sido recargado hace menos de 30 dias permite entar.
  public static boolean bono (int dias){
    boolean validez= (dias>30)? false:true;
  return validez;  //devuelve un valor booleano verdaero o falso si cumple la condición.       
  }
  //función que te dice las posibilidades de ir de una estación a otra sin necesidad de hacer trasbordos
  public static void ruta (String est1,String est2, String [][]lineas){
    boolean res=false;
    for (int i=0;i<lineas.length;i++){
      /*Comprueba que la estación inicial y la final están en la misma linea de la matriz. Si es así dependiendo de la posicion de las estaciones en la linea de la matriz
      establece un sentido y el número de paradas que necesita para llegar al destino*/
      for (int j=0;j<lineas[i].length;j++){
        if (est1.equalsIgnoreCase(lineas[i][j])){
          for (int x=0;x<lineas[i].length;x++){
            if (est2.equalsIgnoreCase(lineas[i][x])){
              res=true;
              int inicio=j<x? j : x;
              int fin=(inicio==j)? x:j;
              int sentido=(fin==x)?(lineas[i].length-1):0;
              String paradas= ((lineas[i].length-(fin-inicio))==1)? "1 parada. ": ((lineas[i].length-(fin-inicio))+" paradas. ");
              String tiempo=((lineas[i].length-(fin-inicio))*3+ " minutos en recorrer ");
              // Si la linea donde están ambas estaciones es la 6 comprueba el sentido más corto, ya que esta es circular.
              if ((j<x)&&((fin-inicio)>(lineas[i].length/2))&&(i==5)){
                  boolean encontrado=false;
                  int a1=j;
                  if (j!=0){
                    System.out.println("Para ir desde "+ est1 + " hasta " + est2+ " debe coger la línea 6 en sentido hacia "+lineas[i][j-1]+": tardará "+ tiempo + paradas);
                  }
                  else {
                    System.out.println("Para ir desde "+ est1 + " hasta " + est2+ " debe coger la línea 6 en sentido hacia "+lineas[i][lineas[i].length-1]+": tardará "+ tiempo +paradas);
                  }
                  while (!encontrado){
                    if (a1==-1){
                      a1=(lineas[i].length-1);
                    }
                    System.out.println(lineas[i][a1]);
                    if (a1==x){
                      encontrado=true;
                    }
                    a1--;
                  }
                }
              else if((j>x)&&((fin-inicio)>(lineas[i].length/2))&&(i==5)){
                boolean encontrado=false;
                int a1=j;
                if (j!=lineas[i].length-1){
                  System.out.println("Para ir desde "+ est1 + " hasta " + est2+ " debe coger la línea "+ (i+1) +" en sentido hacia "+lineas[i][j+1]+": tardará "+tiempo + paradas);
                }
                else {
                  System.out.println("Para ir desde "+ est1 + " hasta " + est2+ " debe coger la línea "+ (i+1)+" en sentido hacia "+lineas[i][0]+": tardará "+ tiempo + paradas);
                }
                while (!encontrado){
                  if (a1==lineas[i].length){
                    a1=0;
                  }
                  System.out.println(lineas[i][a1]);
                  if (a1==x){
                    encontrado=true;
                  }
                  a1++;
                }
              }
              else {
                if(i==5){
                    sentido=(fin==x)?(j+1):j-1;
                }
                String paradas1= (fin-inicio==1)? "1 parada.": ((fin-inicio)+" paradas.");
                String tiempo1=((fin-inicio)*3 + " minutos en recorrer ");
                System.out.println("\nPara ir desde " +est1 + " hasta " + est2 + " puede coger la línea "+ (i+1)+ ", sentido hacia "+ lineas[i][sentido]+ ": tardará " + tiempo1 + paradas1 );
                if (j<x){
                  for (int a=j;a<=x;a++){
                    System.out.println(lineas[i][a]);
                  }
                  System.out.println("\n\n");
                }
                else{
                  for (int a=j;a>=x;a--){
                    System.out.println(lineas[i][a]);
                  }
                }
              }
            }
          }
        } 
      }
    }
    //Si no están las dos estaciones en la misma linea, buscamos estaciones en común para establecer la estación donde se va a efectuar el trasbordo
    if (!res){
      boolean interseccion=false;
      for(int i= 0; i<lineas.length; i++){
        for(int j= 0; j<lineas[i].length; j++){
          if(lineas[i][j].equalsIgnoreCase(est1)){
            for(int k= 0; k<lineas.length; k++){
              for(int w= 0; w<lineas[k].length; w++){
                if(lineas[k][w].equalsIgnoreCase(est2)){
                  for(int x=0 ; x<lineas[i].length; x++){
                    for(int y=0 ; y<lineas[k].length; y++){
                      if(lineas[i][x].equalsIgnoreCase(lineas[k][y])){
                        interseccion= true;
                        int inicio2=j<x ? j : x;
                        int fin2=(inicio2==j)? x:j;
                        int sentido2=(fin2==x)?(lineas[i].length-1):0;
                        int inicio3=w<y ? w : y;
                        int fin3=(inicio3==w)? y:w;
                        int sentido3=(fin3==y)?0:(lineas[k].length-1);
                        String tiempo2=(((fin2-inicio2)+(fin3-inicio3))*3+ " minutos en recorrer ");
                        String paradas2= (fin2-inicio2==1)? "1 parada": ((fin2-inicio2)+" paradas");
                        String paradas3= (fin3-inicio3==1)? "1 parada": ((fin3-inicio3)+" paradas");
                        System.out.println("Para ir de " + est1+" a "+est2+" puedes coger la linea  "+ (i+1) + ", sentido " + lineas[i][sentido2] + ", y hacer trasbordo en "+ lineas[i][x] + " ("+ paradas2+")" + ". Después coge la linea " + (k+1) +" sentido " + lineas[k][sentido3]+" ("+paradas3+").");
                        System.out.println("Conclusión: tardará "+ tiempo2 + ((fin2-inicio2) +(fin3-inicio3))+" paradas");
  // Para mostrar al usuario lo que debe de hacer dividimos el trayecto en dos partes. Y repetimos el proceso hecho antes cuando pertenecían a la misma linea.
                        if (j<x){
                          for (int a=j;a<=x;a++){
                            System.out.println(lineas[i][a]);
                          }
                        }
                        else{
                          for (int a=j;a>=x;a--){
                            System.out.println(lineas[i][a]);
                          }
                        }
                        System.out.println("----Trasbordo----");
                        if (w<y){
                          for (int a=y;a>=w;a--){
                            System.out.println(lineas[k][a]);
                          }
                        }
                        else{
                          for (int a=y;a<=w;a++){
                            System.out.println(lineas[k][a]);
                          }
                        }
                        System.out.println("");
                      }
                    }
                  }
                }
              }
            }
          }
        }   
      }
      if(!interseccion){
        System.out.println("Hay que hacer más de un trasbordo.");
      }
    }
  }
     //funcion actualizacion de aforo
  /*Al estar asociadas las posiciones del array estaciones con el aforo, una vez identificada la posición de la estación, si se cumplen la condiciones
    se suma el número de personas recibidas, modificando la matriz del aforo. Y devuelve el array aforo modificado */
  public static int[] actualizarAforo(int personas,String estacion, String [] estaciones, int[] aforo,int aforomax){
    boolean encontrado= false;
    for(int i = 0; i<estaciones.length && !encontrado; i++){  
      if (estacion.equalsIgnoreCase(estaciones[i])){
        encontrado=true;
        if (aforo[i]+personas<= aforomax){
          aforo[i]+=personas;
        }
      }     
    }
    return aforo; 
  }
  //funcion permitir acceso
  //comprueba que el aforo de cada estación más el número de personas es menor que el aforo máximo, recoriiendo el array buscando la estación y sumando a su aforo asociado el numero de personas.
  public static boolean permitirAcceso(int personas,String estacion, String [] estaciones, int[] aforo,int aforomax){
    boolean acceso = false;
    boolean encontrado= false;
    for(int i = 0; i<estaciones.length && !encontrado; i++){
      if (estacion.equalsIgnoreCase(estaciones[i])){
        encontrado=true;
        if (aforo[i]+personas<=aforomax){
          acceso=true;
        }
      }
    }
    return acceso;//devuelve valor booleano
  }
  //función recursiva para imprimir las estaciones
  //Función recursiva que imprime cada elemento del array hasta que llega al último elemento.
  public static void imprimirEstaciones (String[] lista,int n){
    if (lista.length-1>n){
      System.out.print(lista[n]+ ", ");
      imprimirEstaciones(lista,n+1);
    }
    else{
      System.out.println(lista[n]+ ".");
    }  
  }
  // funcion inicializar aforo
  //Función que recorre cada posicion del array aforo cambiando cada valor 0 por uno aleatorio.
  public static int [] inicializarAforo (int [] aforo){
    for (int i=0;i<aforo.length;i++){
      aforo[i]=(int)(Math.random()*(151 - 50))+50;
    }
    return aforo;//Devuelve la matriz aforo actualizada.
  }
  // funcion permitir aforo lineas
  //Recorre la matriz lineas, por linea de la matriz busacando las estaciones que la componen y asociando cada estación con su aforo, y almacenando la suma de esos aforos en un array aux.
  public static boolean permitirAforoLineas(String est1,String []estaciones,String [][] lineas,int [] aforo, int personas){  
    boolean res= true;
    int[] arrayaux= new int[lineas.length];
    for (int i=0;i<lineas.length;i++){
      for (int j=0;j<lineas[i].length;j++){
        if (est1.equalsIgnoreCase(lineas[i][j])){
          for(int x=0; x<lineas[i].length;x++){
            for(int y=0; y<estaciones.length; y++){
              if(lineas[i][x].equalsIgnoreCase(estaciones[y])){
                arrayaux[i]+=aforo[y];
                }
              }
            }
          }
        }
      }
    //Se recorre el array verificando que los aforos por lineas son menores de lo esatblecido.
    for(int i=0; i<arrayaux.length && res; i++){
      if((arrayaux[i]+personas)>((lineas[i].length)*175)){
        res=false;
      }
    }
    return res;//Devuelve valor booleano
  }
  //aforo lineas
  //Void que muestra por pantalla el aforo de cada linea. Recorre la matriz lineas, por linea de la matriz busacando las estaciones que la componen y asociando cada estación con su aforo, y almacenando la suma de esos aforos en un array aux. 
  public static void aforoLineas (String [] estaciones,int [] aforo,String [][]lineas){
    int[] arrayaux= new int[lineas.length];
    for (int i=0;i<lineas.length;i++){
      for (int j=0;j<lineas[i].length;j++){
        for(int y=0; y<estaciones.length; y++){
          if(lineas[i][j].equalsIgnoreCase(estaciones[y])){
            arrayaux[i]+=aforo[y];
          }
        }
      }
    }
    //imprime por pantalla las posiciones del array.
    for (int i=0;i<arrayaux.length;i++){
      System.out.println("El aforo de la línea " + (i+1)+ " es: " + arrayaux[i]+ " personas.");
    }
  }
  //función inicializar reseña
  //Función que recoore el elmento a elemento la matriz reseñas cambaindo los valores a uno aleatorios.
  public static int [][] inicializarReseña (int [][] reseñas){
    for (int i=0;i<reseñas.length;i++){
      for(int j=0;j<reseñas[i].length;j++){
        reseñas[i][j]=(int)(Math.random()*(11-5)+5);
      }
    }
    return reseñas;//Devuelve la matriz reseñas modificada.
  }
  //función media puntuación
  //función que suma los elmentos de la fila de la matriz reseñas y las divide por el numero de posiciones que tiene la fila.
  public static double [] mediaPuntuacion (int [][] reseñas){
    double [] arrayaux= new double [reseñas.length];
    for (int i=0;i<reseñas.length;i++){
      for (int j=0;j<reseñas[i].length;j++){
        arrayaux[i]+=reseñas[i][j];  
      }
      arrayaux[i]=arrayaux[i]/reseñas[i].length;
    }
    return arrayaux;// Devuelve un array con las medias de las reseñas.
  }
  //función añadir reseña
  //función que permite añadir una reseña, desplaza las demás una posición atrás, de manera que aparecen las 200 reseñas más recientes. 
  public static int [][] añadirReseña (int [][] reseñas, String [] estaciones, int n, String est){
    for (int i=0;i<estaciones.length;i++){
      if (estaciones[i].equalsIgnoreCase(est)){
        for (int j=0;j<reseñas[i].length;j++){
          if ((j<reseñas[i].length-1)){
            reseñas[i][j]=reseñas[i][j+1];
          }
          else{
            reseñas [i][j]=n;
          }
        }
      }
    }
    return reseñas;//devuelve la matriz reseñas modificada
  }
  //funcion volver
  //función que recibe un string, si este es igual a "volver" el booleano que devuelve será true, en caso contrario devolverá un false
  public static boolean volver(String palabra){
    boolean res; 
    res= palabra.equalsIgnoreCase("volver") ? true : false;
    return res;//devuelve un valor booleano
  }
  //funcion mostrar media de una estación
  //función que te imprime por pantalla la reseña de la estación que se solicite. Al ser un void, esta función no devuelve nada.
  public static void mostrarReseña (String []estaciones,double [] medias,String est){
    boolean encontrado=false;
    for (int i=0;i<estaciones.length && !encontrado;i++){
      if (estaciones[i].equalsIgnoreCase(est)){
        System.out.println(medias[i]);
        encontrado=true;
      }
    }
  }
  //función avisoaforo
  //Función void que avisa al usuario antes de viajar de las estaciones cuyo aforo ya está completo.
  public static void avisoAforo (String [] estaciones,int []aforo,int aforomax){
    System.out.println("");
    for (int i=0;i<aforo.length;i++){
      if (aforo[i]==aforomax){
          System.out.println("AVISO: El aforo de " + estaciones[i] + " está completo.");
      }
    }
    System.out.println("");
  }
  //Main
  public static void main (String [] args){
     Scanner sc=new Scanner(System.in);
     //inicializamos unas listas de Strings, que serán las líneas de metro, compuestas cada una con sus respectivas estaciones.
     String [] lin1={"Pinar de Chamartín","Chamartín","Plaza de Castilla","Tetuán","Cuatro Caminos","Bilbao","Tribunal","Gran vía","Sol","Tirso de Molina","Atocha","Pacífico","Puente de Vallecas","Alto del Arenal","Villa de Vallecas","La Gavia","Valdecarros"};
     String [] lin2={"Las Rosas","Alsacia","La Almudena","Ventas","Manuel Becerra","Goya","Príncipe de Vergara","Retiro","Banco de España","Sevilla","Sol","Ópera","Noviciado","San Bernardo","Canal","Cuatro Caminos"};
     String [] lin3={"Villaverde Alto","Ciudad de los Ángeles","Hospital 12 de Octubre","Legazpi","Delicias","Embajadores","Lavapiés","Sol","Callao","Plaza de España","Ventura Rodríguez","Argüelles","Moncloa"};
     String [] lin4={"Argüelles","San Bernardo","Bilbao","Alonso Martínez","Colón","Serrano","Velázquez","Goya","Diego de León","Avenida de América","Alfonso XIII","Avenida de la Paz","Arturo Soria","Mar de Cristal","Hortaleza","Pinar de Chamartín"};
     String [] lin5={"Alameda de Osuna","Canillejas","Suanzes","Pueblo Nuevo","El Carmen","Ventas","Diego de León","Núñez de Balboa","Alonso Martínez","Chueca","Gran vía","Callao","Ópera","Embajadores","Pirámides","Oporto","Carabanchel","Aluche","Empalme","Casa de Campo"};
     String [] lin6={"Moncloa","Ciudad Universitaria","Vicente Aleixandre","Guzmán el Bueno","Cuatro Caminos","Nuevos Ministerios","Avenida de América","Diego de León","Manuel Becerra","O'Donnell","Sainz de Baranda","Conde de Casal","Pacífico","Méndez Álvaro","Legazpi","Usera","Plaza Elíptica","Oporto","Laguna","Alto de Extremadura","Príncipe Pío","Argüelles"};
     String [] lin7={"Hospital de Henares","Jarama","La Rambla","Las Musas","San Blas","Ascao","Pueblo nuevo","Parque de las Avenidas","Avenida de América","Gregorio Marañón","Canal","Guzmán el Bueno","Antonio Machado","Lacoma","Pitis"};
     String [] lin8={"Nuevos Ministerios","Colombia","Pinar del Rey","Mar de Cristal","Feria de Madrid","Aeropuerto T1 T2 T3","Barajas","Aeropuerto T4"};
     String [] lin9={"Paco de Lucía","Barrio del Pilar","Plaza de Castilla","Pío XII","Colombia","Avenida de América","Núñez de Balboa","Príncipe de Vergara","Ibiza","Sainz Baranda","Astilleros","Valdebernardo","Puerta de Arganda","Rivas","Arganda del Rey"};
     String [] lin10={"Hospital Infanta Sofía","Manuel de Falla","La Moraleja","Montecarmelo","Tres Olivos","Fuencarral","Chamartín","Plaza de Castilla","Cuzco","Santiago Bernabéu","Nuevos Ministerios","Gregorio Marañón","Alonso Martínez","Tribunal","Plaza de España","Príncipe Pío","Lago","Batán","Casa de Campo","Colonia Jardín","Cuatro Vientos","Puerta del Sur"};
     String [] lin11={"La Fortuna","La Peseta","Carabanchel Alto","San Francisco","Pan Bendito","Abrantes","Plaza Elíptica"};
     //inicializamos la matriz lineas, cuyas filas serán las distintas líneas de metro.
     String [][] lineas={lin1,lin2,lin3,lin4,lin5,lin6,lin7,lin8,lin9,lin10,lin11};
     //Inicializamos el array "estaciones" que está formado por las estaciónes del metro de madrid.
     String []estaciones={"Pinar de Chamartín","Chamartín","Plaza de Castilla","Tetuán","Cuatro Caminos","Canal","Bilbao","Tribunal","Gran vía","Sol","Tirso de Molina","Atocha","Pacífico","Puente de Vallecas","Alto del Arenal","Villa de Vallecas","La Gavia","Valdecarros","Las Rosas","Alsacia","La Almudena","Ventas","Manuel Becerra","Goya","Príncipe de Vergara","Retiro","Banco de España","Sevilla","Ópera","San Bernardo","Villaverde Alto","Ciudad de los Ángeles","Hospital 12 de Octubre","Legazpi","Delicias","Embajadores","Lavapiés","Callao","Plaza de España","Ventura Rodríguez","Argüelles","Moncloa","Alonso Martínez","Colón","Serrano","Velázquez","Diego de León","Avenida de América","Alfonso XIII","Avenida de la Paz","Arturo Soria","Mar de Cristal","Hortaleza","Alameda de Osuna","Canillejas","Suanzes","Pueblo Nuevo","El Carmen","Núñez de Balboa","Chueca","Pirámides","Oporto","Carabanchel","Aluche","Empalme","Casa de Campo","Ciudad Universitaria","Vicente Aleixandre","Guzmán el Bueno","Nuevos Ministerios","O´Donnell","Sainz de Baranda","Conde de Casal","Méndez Álvaro","Usera","Plaza Elíptica","Laguna","Alto de Extremadura","Príncipe Pío","Hospital de Henares","Jarama","La Rambla","Las Musas","San Blas","Ascao","Parque de las Avenidas","Gregorio Marañón","Antonio Machado","Lacoma","Pitis","Colombia","Pinar del Rey","Feria de Madrid","Aeropuerto T1 T2 T3","Barajas","Aeropuerto T4","Paco de Lucía","Barrio del Pilar","Pío XII","Ibiza","Astilleros","Valdebernardo","Puerta de Arganda","Rivas","Arganda del Rey","Hospital Infanta Sofía","Manuel de Falla","La Moraleja","Montecarmelo","Tres Olivos","Fuencarral","Cuzco","Santiago Bernabéu","Lago","Batán","Colonia Jardín","Cuatro Vientos","Puerta del Sur","La Fortuna","La Peseta","Carabanchel Alto","San Francisco","Pan Bendito","Abrantes"};
     //inicializamos el siguiente array de enteros, cuya longitud será idéntica al array de estaciones.
     int [] aforo=new int [estaciones.length];
     aforo=inicializarAforo(aforo);
     int aforomax=200;
     //inicializamos la matriz reseñas que tendrá tantas filas como estaciones de metro y 200 columnas (200 reseñas).
     int [][] reseñas=new int [estaciones.length][200];
     reseñas=inicializarReseña(reseñas);
     double [] medias=mediaPuntuacion(reseñas);
     int menu;
     System.out.println("\n#     # ####### ####### ######  #######    #     #    #    ######  ######  ### ######\n##   ## #          #    #     # #     #    ##   ##   # #   #     # #     #  #  #     #\n# # # # #          #    #     # #     #    # # # #  #   #  #     # #     #  #  #     #\n#  #  # #####      #    ######  #     #    #  #  # #     # #     # ######   #  #     #\n#     # #          #    #   #   #     #    #     # ####### #     # #   #    #  #     #\n#     # #          #    #    #  #     #    #     # #     # #     # #    #   #  #     #\n#     # #######    #    #     # #######    #     # #     # ######  #     # ### ######");
     System.out.println("\nBienvenido a la aplicación para gestionar el aforo del Metro de Madrid");
     //Menú
     //El menú está compuesto por otros submenús cuya finalidad es llamar a las distintas funciones.
     do {
       System.out.println("\nMENÚ: \n1)Viajar \n2)Registrar salida\n3)Ruta \n4)Estaciones \n5)Consulta de aforos \n6)Ayuda \n7)Reseña \n8)Cerrar app \n¿Qué desea hacer?");
       menu=sc.nextInt();
       switch (menu) {
      case 1:
        avisoAforo(estaciones,aforo,aforomax);
        String est;
        est=sc.nextLine();
        do {
         System.out.println("Seleccione una estación válida (o \"volver\" para ir al menú principal)");
         est=sc.nextLine();
        }while (!(comprobarEstacion(est,estaciones))&& !volver(est));
        if (!volver(est)){
          String destino;
          do {
            System.out.println("Seleccione un destino válido (o \"volver\" para ir al menú principal)");
            destino=sc.nextLine();
          }while ((!(comprobarEstacion(destino,estaciones))||(destino.equals(est)))&& !volver(destino));
          if (!volver(destino)){
            int pers;
            do{
              System.out.println("¿Cuántas personas van a entrar?");
              pers=sc.nextInt();
              if (pers<0){
                System.err.println("El número introducido no es válido.");
              }
            }while(pers<0);
            int diasbono;
            int cont=0;
            for (int i=0;i<pers;i++){
              do{
                System.out.println("¿Hace cuántos días recargó el bono la " + (i+1)+ "ª persona");
                diasbono=sc.nextInt();
                if (diasbono<0){
                  System.err.println("número de días incorrecto.");
                }
              }while(diasbono<0);
              if (!bono(diasbono)){
                System.err.println("Usted no puede entrar, necesita recargar su bono.");
              }
              else{
                System.out.print("Bono válido, le quedan ");
                System.err.print((30-diasbono)+" días para que expire.\n");                 
              }
              cont+=bono(diasbono)? 1:0;
            }
            if (permitirAcceso(cont,est,estaciones,aforo,aforomax) && permitirAcceso(cont,destino,estaciones,aforo,aforomax)){
              if(permitirAforoLineas( est,estaciones,lineas, aforo, cont)&& permitirAforoLineas( destino,estaciones,lineas, aforo, cont)){
                String entran2=(pers!=cont) ? ("Entran " + cont +" de " + (pers)) : ("Puede entrar: " + cont + ".");
                System.out.println(entran2); 
                aforo=actualizarAforo(cont,est,estaciones,aforo,aforomax);                                            
                aforo=actualizarAforo(cont,destino,estaciones,aforo,aforomax);
              }
              else{
                System.out.println("Una de las estaciones seleccionas pertenece a una linea con el aforo completo.");
              } 
            }
            else{
              System.out.println("Una o las dos estaciones seleccionadas tienen el aforo al máximo. Por favor espere a que se reduzca el número de personas.");
            }
          }
        }
        break;
      case 2:
        String estcaso2;
        estcaso2=sc.nextLine();
        do {
          System.out.println("Seleccione la estación (válida) de la que va a salir (o \"volver\" para ir al menú principal)");
          estcaso2=sc.nextLine();
        }while (!(comprobarEstacion(estcaso2,estaciones))&&!volver(estcaso2));
        if (!volver(estcaso2)){
          String destinocaso2;
          do {
            System.out.println("Seleccione su procedencia (válida) (o \"volver\" para ir al menú principal)");
            destinocaso2=sc.nextLine();
          }while ((!(comprobarEstacion(destinocaso2,estaciones)||(destinocaso2.equalsIgnoreCase(estcaso2))))&&!volver(destinocaso2));
          if (!volver(destinocaso2)){
            int perscaso2;
            do{
              System.out.println("¿Cuántas personas van a salir? (número válido)");
              perscaso2=sc.nextInt();
              if (perscaso2<0){
                System.err.println("El número introducido no es válido");
              }
            }while(perscaso2<0);
            aforo=actualizarAforo(-(perscaso2),estcaso2,estaciones,aforo,aforomax);
            aforo=actualizarAforo(-(perscaso2),destinocaso2,estaciones,aforo,aforomax);
            System.out.println("Gracias por usar el metro.");
          }
        }
        break;
      case 3:
        int opcioncaso3;
        do {
          System.out.println("\n1)¿Cómo llegar?.\n2)Mapa esquemático lineas de metro.\n3)Volver al menú principal. \n¿Qué desea hacer?");
          opcioncaso3=sc.nextInt();
          switch (opcioncaso3) {
            case 1:
              String estcaso3;
              estcaso3=sc.nextLine();
              do {
                System.out.println("Seleccione una estación válida");
                estcaso3=sc.nextLine();
              }while (!(comprobarEstacion(estcaso3,estaciones)));
              String destinocaso3;
              do {
                System.out.println("Seleccione un destino válido");
                destinocaso3=sc.nextLine();
              }while (!(comprobarEstacion(destinocaso3,estaciones)));
              ruta(estcaso3,destinocaso3,lineas);
            break;
            case 2:
              for (int i=0;i<lineas.length;i++){
                  System.out.print("Línea "+ (i+1)+ ":\t");
                for (int j=0;j<lineas[i].length;j++){
                  if (j==lineas[i].length-1){
                   System.out.print(lineas[i][j]);
                  }
                  else {
                    System.out.print(lineas[i][j]+ "----");
                  }
                    
                }
                System.out.println("");
                System.out.println("");
              }
              break;
            case 3:
              break;
            default:
              System.out.println("Introduzca un número correcto");
          } 
        }while (opcioncaso3!=3);
        break;
      case 4:
        imprimirEstaciones(estaciones,0);
        break;
      case 5:
        int opcioncaso5;
        do {
          System.out.println("\n1)Aforo de cada estación.\n2)Aforo de cada línea de metro.\n3)Volver al menú principal. \n¿Qué desea hacer?");
          opcioncaso5=sc.nextInt();
          switch (opcioncaso5) {
            case 1:
              for (int i=0;i<estaciones.length;i++){
                 System.out.println("Aforo " + estaciones[i]+ ": "+ aforo[i] + " personas.");
              }
            break;
            case 2:
              aforoLineas(estaciones,aforo,lineas); 
              break;
            case 3:
              break;
            default:
              System.out.println("Introduzca un número correcto");
          } 
        }while (opcioncaso5!=3);
        break;
      case 6:
        int opcioncaso6;
        do {
        System.out.println("Elija: \n1)Funcionalidad aplicación \n2)Normativa Covid \n3) Volver al menú pricipal ");
        opcioncaso6=sc.nextInt();
        switch (opcioncaso6) {
          case 1:
            System.out.println("Funcionalidad de la aplicación:\n Las dos primeras opciones del menú favorecen el control del aforo del metro con respecto al aforo máximo permitido por el COVID-19 gracias a la aportación de sus datos (únicamente utilizados de forma anónima)\n La opción ruta permite al usuario informarse de las distintas posibilidades para llegar a su destino (confiando en la inteligencia del usuario para que elija la más rápida).\n La opcíon de reseñas permite valorar de forma anónima las estaciones y ayudar así a la gestión del aforo del metro de Madrid.");
            break;
          case 2:
            System.out.println("La mascarilla es obligatoria a partir de los 6 años y recomendable para los niños de entre 3 y 5 años. \nSe contemplan algunas excepciones, como las personas con dificultad respiratoria o aquellas con discapacidad o dependencia a las que su uso pueda resultar perjudicial, aplicando la normativa ministerial. En estos casos los viajeros siempre deben portar justificación médica. \nSe han instalado carteles en estaciones y trenes con recomendaciones y recordatorios de la obligatoriedad del uso de la mascarilla.\nSe han instalado 200 dispensadores de gel hidroalcohólico en las 50 estaciones más transitadas, situados en los vestíbulos, pasados los tornos de entrada. Listado de estaciones que cuentan con dispensadores de hidrogel.  \nSe han instalado máquinas de venta de mascarillas e hidrogel en algunas de las estaciones con más afluencia de viajeros: Moncloa, Nuevos Ministerios, Príncipe Pío, Plaza de España, Argüelles, Diego de León, Atocha-Renfe, Chamartín, Sainz de Baranda, Colombia. Las mascarillas son lavables y reutilizables hasta 10 veces. \nNo está permitido el consumo de alimentos en el interior de la red de Metro, para evitar que los usuarios se quiten la mascarilla para comer.\nEl aforo máximo por estación es de 200 personas y el de cada linea es de 175*cada estación que la componen.\n No esta permitido acceder a una estación o linea con el aforo completo.");
            break;
          case 3:
            break;
          default:
            System.out.println("Introduzca un número correcto");
        }
        }while (opcioncaso6!=3);
        break;
      case 7:
        int opcioncaso7;
        String estpuntuar,estmedia;
        int puntuacion;
        do{
          System.out.println("\nElija: \n1)Añadir puntuación. \n2)Ver la puntuación media de una estación de sus últimas 200 reseñas. \n3)Volver al menú pricipal.");
        opcioncaso7=sc.nextInt();
        switch (opcioncaso7) {
          case 1:
          estpuntuar=sc.nextLine();
          do {
            System.out.println("¿Qué estación desea puntuar? (estación válida)");
            estpuntuar=sc.nextLine();
          }while (!(comprobarEstacion(estpuntuar,estaciones)));
          do {
            System.out.println("Introduzca una puntuación entera siendo 0 el mínimo y el 10 el máximo");
            puntuacion=sc.nextInt();
          }while (puntuacion<0 || puntuacion>10);
          reseñas=añadirReseña(reseñas,estaciones,puntuacion,estpuntuar);
          medias=mediaPuntuacion(reseñas);
          System.out.println("Gracias por ayudarnos a mejorar nuestro servicio.");
          break;
          case 2:
            estmedia=sc.nextLine();
            do {
            System.out.println("¿De qué estación desea ver la puntuación media de las últimas 200 valoraciones? (estación válida)");
            estmedia=sc.nextLine();
            mostrarReseña (estaciones,medias,estmedia);
          }while (!(comprobarEstacion(estmedia,estaciones)));
          
          break;
          case 3:
            break;
          default:
            System.out.println("Introduzca un número correcto");
        }
        }while(opcioncaso7!=3);
        
        break;
      case 8:
        System.out.println("Gracias por confiar en nosotros, ¡hasta pronto! :) ");
        break;
      default:
        System.out.println("Introduzca un número correcto: ");
     }  
     } while ((menu!=8));
  }
}