package fr.sii.nosql.server.repository.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public abstract class FileRepository<T> {

	protected String repositoryPath;

	protected final String suffix;

	public FileRepository(
			@Value("${filemoviesrepo.path}") String repositoryPath,
			String suffix) {
		super();
		this.repositoryPath = repositoryPath;
		this.suffix = suffix;
	}

	protected abstract T load(File f);

	protected Long extractId(File file) {
		String fileName = file.getName();
		int posPoint = fileName.lastIndexOf('.');
		if (0 < posPoint && posPoint <= fileName.length() - 2) {
			return Long.parseLong(fileName.substring(0, posPoint));
		}
		return null;
	}

	public T findOne(Long id) {
		T one = null;
		File file = getFile(id);
		if (file != null) {
			one = (T) load(file);
		}
		return one;
	}

	public long count() {
		List<File> files = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			File dir = new File(repositoryPath + File.separator + i);
			files.addAll(Arrays.asList(dir.listFiles()));
		}
		return files.size();
	}

	public Iterable<T> all() {
		final List<File> files = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			File dir = new File(repositoryPath + File.separator + i);
			files.addAll(Arrays.asList(dir.listFiles()));
		}

		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					int i = 0;// next movie index

					@Override
					public boolean hasNext() {
						return i < files.size();
					}

					@Override
					public T next() {
						try {
							return load(files.get(i++));
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}

	public boolean exists(Long id) {
		File file = getFile(id);
		return file.exists();
	}

	protected File getFile(Long id) {
		return new File(repositoryPath + File.separator + (id / 100000)
				+ File.separator + id + suffix);
	}
}