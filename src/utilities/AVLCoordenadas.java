package utilities;


public class AVLCoordenadas {
	public class ARVORE{
		 Coord dado;
		 ARVORE dir;
		 ARVORE esq;
		 int hEsq, hDir;
		}


	public ARVORE root = null;
	
	public ARVORE rotacaoDireita (ARVORE p){
		// faz rota��o para direita em rela��o ao n� apontado por p	
			ARVORE q,temp;
			q = p.esq;
			temp = q.dir;
			q.dir = p;
			p.esq = temp;
			return q;
		}
	
	public ARVORE rotacaoEsquerda(ARVORE p) {
		// faz rota��o para esquerda em rela��o ao n� apontado por p
			ARVORE q,temp;
			q = p.dir;
			temp = q.esq;
			q.esq = p;
			p.dir = temp;
			return q;
			}

	
	public ARVORE balanceamento (ARVORE p) {
		// analisa FB e realiza rota��es necess�rias para balancear �rvore
		int FB = p.hDir - p.hEsq;
		if (FB > 1) {
			int fbFilhoDir = p.dir.hDir - p.dir.hEsq;
			if (fbFilhoDir >= 0)
				p = rotacaoEsquerda(p);
			else {
				p.dir = rotacaoDireita(p.dir);
				p = rotacaoEsquerda(p);
			}
		}
		else {
			if (FB < -1) {
				int fbFilhoEsq = p.esq.hDir - p.esq.hEsq;
				if (fbFilhoEsq <= 0)
					p = rotacaoDireita(p);
				else {
					p.esq = rotacaoEsquerda(p.esq);
					p = rotacaoDireita(p);
				}
			}
		}
		return p;
	}

	

	public ARVORE inserirAVL(ARVORE p, Coord coordenadas) {
		if (p == null) { //n� inserido sempre ser� n� folha
			p=new ARVORE();
			p.dado = coordenadas;
			p.esq = null; p.dir = null;
			p.hDir=0; p.hEsq=0;
		}
		else if (p.dado.valor > coordenadas.valor){
			p.esq= inserirAVL (p.esq, coordenadas);
			if (p.esq.hDir > p.esq.hEsq) //Altura do n� ser� a maior
				p.hEsq = p.esq.hDir + 1; //altura dos seus filhos
			else
				p.hEsq = p.esq.hEsq + 1;
			p = balanceamento(p);
		}
		else {
			p.dir=inserirAVL(p.dir, coordenadas);
			if (p.dir.hDir > p.dir.hEsq)
				p.hDir = p.dir.hDir + 1;
			else
				p.hDir = p.dir.hEsq + 1;
			p = balanceamento(p);
		}
		return p;
	}

	public void atualizaAlturas(ARVORE p) {
		/*atualiza informa��o da altura de cada n� depois da remo��o percorre a �rvore usando percurso p�s-ordem para ajustar primeiro os n�s folhas (profundidade maior) e depois os n�veis acima */ 
		  if( p != null) {
			atualizaAlturas(p.esq);
			if (p.esq == null)
				p.hEsq = 0;
			else  if (p.esq.hEsq > p.esq.hDir)
				  p.hEsq = p.esq.hEsq+1;
			     else
				  p.hEsq = p.esq.hDir+1;
			atualizaAlturas(p.dir);
			if (p.dir == null)
				p.hDir = 0;
			else if (p.dir.hEsq > p.dir.hDir)
				 p.hDir = p.dir.hEsq+1;
			     else
				 p.hDir = p.dir.hDir+1;
			}
		}


	public void mostraFB(ARVORE p) {
		if (p != null) {
			if (p.esq != null)
				mostraFB(p.esq);
			if(p.dir!=null)
				mostraFB(p.dir);
			//FB = hDir - hEsq
			System.out.println("FB (n� "+ p.dado.valor + ". Coordenada: " + p.dado.coordenadas +"): "+ (p.hDir - p.hEsq));
		}
	}
}
