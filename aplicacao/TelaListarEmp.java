package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class TelaListarEmp extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scroller;
	private JButton btnListarEmprestimos;

	/**
	 * Create the frame.
	 */
	public TelaListarEmp() {
		setTitle("Lista de Livros Emprestados no Momento");
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
		scroller.setBounds(24, 29, 450, 282);
		contentPane.add(scroller);
		
		
		btnListarEmprestimos = new JButton("Atualizar");
		btnListarEmprestimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String texto = "";

				ArrayList<Emprestimo> listaEmprestimos = Fachada.listarEmprestimos();
				
				if(listaEmprestimos.isEmpty())
					JOptionPane.showMessageDialog(null,"\nNao ha emprestimos no momento!\n");
				else
				{
					for(Emprestimo em: listaEmprestimos)
						texto+= em.toString() + "\n";
					
					textArea.setText(texto);
				}
				listaEmprestimos = null;
				
				/*********************************************/
			}
		});
		btnListarEmprestimos.setBounds(161, 330, 148, 23);
		contentPane.add(btnListarEmprestimos);

	}
}
