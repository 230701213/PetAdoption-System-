 // Method to load available pets into the table
    private void loadAvailablePets() {
        // Clear existing rows
        tableModel.setRowCount(0);
        
        String sql = "SELECT pet_id, name, breed, age, type, status FROM pets WHERE status = 'Available'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int petId = rs.getInt("pet_id");
                String petName = rs.getString("name");
                String petBreed = rs.getString("breed");
                int petAge = rs.getInt("age");
                String petType = rs.getString("type");
                String petStatus = rs.getString("status");
                // Add pet data to the table
                tableModel.addRow(new Object[]{ petId, petName, petBreed, petAge, petType, petStatus });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading pets: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace for debugging
        }
    }
