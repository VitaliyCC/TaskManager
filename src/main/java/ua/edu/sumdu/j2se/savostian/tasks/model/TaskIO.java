package ua.edu.sumdu.j2se.savostian.tasks.model;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskIO {
    private static final Logger logger =
            Logger.getLogger(TaskIO.class);

    /**
     * Method that writes a collection of objects to OutputStream
     * @param tasks collection with tasks
     * @param out OutputStream
     */
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream outputStream = new DataOutputStream(out)) {
            outputStream.writeInt(tasks.size());

            for (Task task : tasks) {
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeInt(task.isActive() ? 1 : 0);
                outputStream.writeInt(task.getRepeatInterval());
                outputStream.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));

                if (task.isRepeated()) {
                    outputStream.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e + " " + e.getStackTrace().toString() + "\n");
        }
    }

    /**
     * Method reads a collection of tasks from Stream.
     * @param tasks collection for tasks
     * @param in InputStream
     */
    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream inputStream = new DataInputStream(in)) {
            int size = inputStream.readInt();

            for (int i = 0; i < size; i++) {
                inputStream.readInt();

                Task task;
                String title = inputStream.readUTF();
                boolean inactive = inputStream.readInt() == 1;
                int interval = inputStream.readInt();

                if (interval == 0) {
                    task = new Task(title, LocalDateTime.ofEpochSecond(
                            inputStream.readLong(), 0, ZoneOffset.UTC));
                } else {
                    task = new Task(title, LocalDateTime.ofEpochSecond(
                            inputStream.readLong(), 0, ZoneOffset.UTC),
                            LocalDateTime.ofEpochSecond(
                                    inputStream.readLong(), 0, ZoneOffset.UTC), interval);
                }
                task.setActive(inactive);
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e + " " + e.getStackTrace().toString() + "\n");
        }
    }

    /**
     * Method that writes a collection of tasks to binary file
     * @param tasks collection with tasks
     * @param file binary file
     */
    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            write(tasks, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e + " " + e.getStackTrace().toString() + "\n");
        }
    }

    /**
     * Method reads a collection of tasks from binary file
     * @param tasks collection for tasks
     * @param file binary file
     */
    public static void readBinary(AbstractTaskList tasks, File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            read(tasks, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e + " " + e.getStackTrace().toString() + "\n");
        }
    }

    /**
     * Method that writes a collection of objects to WriterStream
     * @param tasks collection with tasks
     * @param out OutputStream
     */
    public static void write(AbstractTaskList tasks, Writer out) {
        Gson gson = new Gson();
        try (BufferedWriter writer = new BufferedWriter(out)) {
            writer.write(gson.toJson(tasks));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e + " " + e.getStackTrace().toString() + "\n");
        }
    }

    /**
     * Method reads a collection of tasks from Stream
     * @param tasks collection for tasks
     * @param in Reader
     */
    public static void read(AbstractTaskList tasks, Reader in) {
        try (BufferedReader reader = new BufferedReader(in)) {
            String text;
            Gson gson = new Gson();
            while ((text = reader.readLine()) != null) {
                AbstractTaskList taskList = gson.fromJson(text,
                        tasks.getClass());
                for (Object task : taskList) {
                    tasks.add((Task) task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e + " " + e.getStackTrace().toString() + "\n");
        }
    }

    /**
     * Method that writes a collection of tasks to gson file
     * @param tasks collection with tasks
     * @param file gson file
     */
    public static void writeText(AbstractTaskList tasks, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            write(tasks, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e + " " + e.getStackTrace().toString() + "\n");
        }
    }

    /**
     * Method reads a collection of tasks from gson file
     * @param tasks collection for tasks
     * @param file gson file
     */
    public static void readText(AbstractTaskList tasks, File file) {
        try (FileReader fileReader = new FileReader(file)) {
            read(tasks, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e + " " + e.getStackTrace().toString() + "\n");
        }
    }
}