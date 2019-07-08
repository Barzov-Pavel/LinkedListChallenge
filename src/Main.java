import javax.security.sasl.SaslClient;
import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        Album album = new Album("Иномарки", "Би-2");
        album.addSong("Полковнику никто не пишет", 4.6);
        album.addSong("Лайки", 3.5);
        album.addSong("Мой-рок-н-рол", 4.3);
        album.addSong("Варвара", 4.8);

        albums.add(album);

        album = new Album("Лунапарк", "Би-2");
        album.addSong("Караоке", 3.2);
        album.addSong("Шар земной", 4.3);
        album.addSong("Черная река", 4.2);
        album.addSong("Шамбала", 3.3);

        albums.add(album);

        LinkedList<Song> playList = new LinkedList<>();
        albums.get(0).addToPlayList("Лайки", playList);
        albums.get(0).addToPlayList("Варвара", playList);
        albums.get(0).addToPlayList("Добро", playList); // не существует
        albums.get(0).addToPlayList(1, playList);
        albums.get(1).addToPlayList(1, playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(0).addToPlayList(25, playList); // не существует

        play(playList);
    }

    private static void play(LinkedList playList) {
        Scanner scanner = new Scanner(System.in);
        boolean isQuit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();
        if (playList.size() == 0) {
            System.out.println("No songs in playlist");
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while (!isQuit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Playlist complete");
                    isQuit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous());
                            forward = false;
                        } else {
                            System.out.println("We are the start of the playlist");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the playlist");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if (playList.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else {
                            System.out.println("Removed last song in playlist");
                        }
                    }

            }
        }
    }

    private static void printMenu() {
        System.out.println("Actions\n0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - available actions\n" +
                "6 - remove song from playlist");
    }

    private static void printList(LinkedList<Song> playlist) {
        Iterator<Song> iterator = playlist.listIterator();
        System.out.println("-------------------------------------");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("-------------------------------------");
    }
}

