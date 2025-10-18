import {Component, signal, inject, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CommonModule} from '@angular/common';
import {PostsService} from "../../../core/services/posts.service";
import {PostResponse} from "../../../core/interfaces/postResponse.interface";
import { BackButtonComponent } from "src/app/components/back-button/back-button.component";


/**
 * Composant d'affichage d'un post spécifique utilisant les signaux Angular 20+
 * Récupère l'ID du post depuis l'URL et affiche le contenu complet
 */
@Component({
  selector: 'app-post',
  imports: [
    CommonModule,
    BackButtonComponent
],
  templateUrl: './post.component.html',
  styleUrl: './post.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PostComponent implements OnInit {
  private route = inject(ActivatedRoute);
  postsService = inject(PostsService);

  /** Signal pour stocker les données du post */
  protected readonly post = signal<PostResponse | null>(null);

  ngOnInit(): void {
    this.loadPost();
  }

  /**
   * Charge le post en fonction de l'ID récupéré depuis l'URL
   */
  loadPost(): void {
    const idParam = this.route.snapshot.paramMap.get('id');

    if (!idParam) {
      return;
    }

    const id = parseInt(idParam, 10);
    if (isNaN(id)) {
      return;
    }

    this.postsService.getPostById(id).subscribe({
      next: (postData: PostResponse) => {
        this.post.set(postData);
      },
      error: (err) => {
        console.error('Erreur lors du chargement du post:', err);
      }
    });
  }
}
