package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Emprestimo;
import modelo.Livro;

public class TelaFazerDev extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scroller;
	private JButton btnListarLivros;
	private JTextField textField_1;
	private JLabel lblID;


	/**
	 * Create the frame.
	 */
	public TelaFazerDev() {
		setTitle("Fazer Devolucao");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroller.setBounds(24, 29, 450, 250);
		contentPane.add(scroller);
		
		lblID = new JLabel("ID");
		lblID.setBounds(20, 330, 148, 23);
		contentPane.add(lblID);
		
		textField_1 = new JTextField();
		textField_1.setBounds(60, 330, 220, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnListarLivros = new JButton("Devolver");
		btnListarLivros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Emprestimo emprestimo;
				if (Fachada.getLogado() != null)
				{
					String strId = textField_1.getText();
					int id = Integer.parseInt(strId);
					try{
						emprestimo = Fachada.criarDevolucao(id); 
						textArea.setText("ID: " + emprestimo.getId() + "\nMulta: " + emprestimo.getMulta() );
					}catch (Exception erro){
						JOptionPane.showMessageDialog(null,erro.getMessage());
					}
					strId = null;
					emprestimo = null;
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"Não há usuários logados\nLogue para desbloquear essa opção!\n");
				}
				
				/*********************************************/
			}
		});
		btnListarLivros.setBounds(300, 330, 148, 23);
		contentPane.add(btnListarLivros);

	}
}
