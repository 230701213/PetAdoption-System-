public PetAdoptionUI() {
        // Set up the database connection
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error connecting to database: " + e.getMessage());
        }
        
        // Set up the frame
        frame = new JFrame("Pet Adoption System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Main panel with BorderLayout
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2, 10, 10)); // 2 columns with space between rows and columns
        
        // Add components for adding a pet
        inputPanel.add(new JLabel("Pet Name:"));
        petNameField = new JTextField();
        inputPanel.add(petNameField);
        
        inputPanel.add(new JLabel("Pet Breed:"));
        petBreedField = new JTextField();
        inputPanel.add(petBreedField);
        
        inputPanel.add(new JLabel("Pet Age:"));
        petAgeField = new JTextField();
        inputPanel.add(petAgeField);
        
        inputPanel.add(new JLabel("Pet Type:"));
        petTypeField = new JTextField();
        inputPanel.add(petTypeField);
        
        addPetButton = new JButton("Add Pet");
        addPetButton.addActionListener(e -> addNewPet());
        inputPanel.add(addPetButton);
        
        // Add components for adopting a pet
        inputPanel.add(new JLabel("Adopter Name:"));
        adopterNameField = new JTextField();
        inputPanel.add(adopterNameField);
        
        adoptPetButton = new JButton("Adopt Pet");
        adoptPetButton.addActionListener(e -> adoptPet());
        inputPanel.add(adoptPetButton);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        
        // Set up the table to display the pets
        String[] columnNames = { "Pet ID", "Pet Name", "Breed", "Age", "Type", "Status" };
        tableModel = new DefaultTableModel(columnNames, 0);
        petTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(petTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(panel);
        
        // Load available pets into the table
        loadAvailablePets();
    }

