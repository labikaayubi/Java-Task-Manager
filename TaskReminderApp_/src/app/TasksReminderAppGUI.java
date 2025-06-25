package app;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TasksReminderAppGUI {

    private JFrame frame;
    private JTextField titleField, dateField;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private TaskManager taskManager;

   
    public TasksReminderAppGUI() {
        taskManager = new TaskManager();
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Task Reminder App");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(255, 128, 192));
        titleField = new JTextField(15);
        titleField.setBackground(new Color(200, 183, 200));
        dateField = new JTextField(10);
        dateField.setBackground(new Color(200, 183, 200));
        JButton addButton = new JButton("Add task");
        addButton.setBackground(new Color(247, 238, 136));
        addButton.setFont(new Font("Tahoma", Font.BOLD, 11));

        JLabel label = new JLabel("Title:");
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        inputPanel.add(label);
        inputPanel.add(titleField);
        JLabel label_1 = new JLabel("Due Date:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        inputPanel.add(label_1);
        inputPanel.add(dateField);
        inputPanel.add(addButton);
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);

    
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.getContentPane().add(new JScrollPane(taskList), BorderLayout.CENTER);

      
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(200, 183, 200));
        JButton completeButton = new JButton("Mark Done");
        completeButton.setBackground(new Color(247, 238, 136));
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(247, 238, 136));
        JButton sortButton = new JButton("Sort by Date");
        sortButton.setBackground(new Color(247, 238, 136));
        JButton viewAllButton = new JButton("View All");
        viewAllButton.setBackground(new Color(247, 238, 136));
        JButton viewPendingButton = new JButton("Pending Only");
        viewPendingButton.setBackground(new Color(247, 238, 136));
        JButton viewCompletedButton = new JButton("Completed Only");
        viewCompletedButton.setBackground(new Color(247, 238, 136));

        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(viewAllButton);
        buttonPanel.add(viewPendingButton);
        buttonPanel.add(viewCompletedButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String dueDate = dateField.getText().trim();
            if (!title.isEmpty() && !dueDate.isEmpty()) {
                Task task = new Task(title, dueDate);
                taskManager.addTask(task);
                taskListModel.addElement(task);
                titleField.setText("");
                dateField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Enter both title and date.");
            }
        });

       
        completeButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                taskManager.markCompleted(index);
                taskList.repaint(); 
            }
        });

    
        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                taskManager.deleteTask(index);
                taskListModel.remove(index);
            }
        });

      
        sortButton.addActionListener(e -> {
            taskManager.sortByDueDate();
            refreshTasks(taskManager.getTasks());
        });

        
        viewAllButton.addActionListener(e -> refreshTasks(taskManager.getTasks()));

       
        viewPendingButton.addActionListener(e -> {
            taskListModel.clear();
            for (Task task : taskManager.getTasks()) {
                if (!task.isCompleted()) {
                    taskListModel.addElement(task);
                }
            }
        });

     
        viewCompletedButton.addActionListener(e -> {
            taskListModel.clear();
            for (Task task : taskManager.getTasks()) {
                if (task.isCompleted()) {
                    taskListModel.addElement(task);
                }
            }
        });

        frame.setVisible(true);
    }

    private void refreshTasks(List<Task> tasks) {
        taskListModel.clear();
        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TasksReminderAppGUI::new);
    }
}



